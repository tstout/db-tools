
# this required gem invocation seems funky to me, but I do like minitest
gem 'minitest'
require 'minitest/autorun'
require 'java'

java_import 'java.sql.Timestamp'
java_import 'java.util.Calendar'

module DbIo
  include_package 'db.io.operations'
  include_package 'db.io.migration'
  include_package 'db.io.config'
end

class TestQuery < MiniTest::Test
  def setup
    @now = Calendar.getInstance.getTimeInMillis

    @connForge = DbIo::Databases.new_conn_factory(DbIo::DBVendor::H2_MEM,
                                                  DbIo::Databases.new_creds().with_db_name('db-test'))
    DbIo::Migrators
    .liquibase(@connForge)
    .update('/db/io/migration/test-changelog.sql')

    @query = DbIo::Queries.new_query(@connForge)

  end

  # Currently, the java proxy-related code for querying only supports interfaces.
  # Ideally, we should be able to declare a record in Ruby as in the following:
  #
  # LogRecord = Struct.new(:id, :when, :msg, :level, :logger, :thread)
  #
  # I may support this in the near future. For the moment the inteface must
  # be declared in java. I have not yet found a way in Jruby to define a java
  # interface, only implement one.

  def test_basic_read_write
    DbIo::Updates.new_update(@connForge,
                             'insert into db_io.logs (when, msg, level, logger, thread) values (?, ?, ?, ?, ?)',
                             Timestamp.new(@now),
                             'test msg',
                             'DEBUG',
                             'test.logger',
                             'test.thread')
    .run

    result = @query.run(DbIo::LogRecord.java_class, 'select * from db_io.logs')

    refute_equal(result.size, 0)
    assert_equal(result.first.msg, 'test msg')
    assert_equal(result.first.level, 'DEBUG')
    assert_equal(result.first.logger, 'test.logger')
    assert_equal(result.first.thread, 'test.thread')
    assert_equal(result.first.when, Timestamp.new(@now))
    assert_instance_of(Fixnum, result.first.id)
  end
end