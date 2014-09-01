gem 'minitest'
require 'minitest/autorun'
require 'java'

java_import 'java.sql.Timestamp'
java_import 'java.util.Calendar'

module DbIo
  include_package 'db.io.operations'
  #include_package 'db.io.h2'
  include_package 'db.io.migration'
  include_package 'db.io.config'
end

class TestQuery < MiniTest::Test
  def setup
    @now = Calendar.getInstance.getTimeInMillis

    @connForge = DbIo::Databases.new_conn_factory(DbIo::Databases::DBVendor::H2_MEM,
                                                  DbIo::Databases.new_creds().with_dbname('db-test'));
        # db.io.core.H2Credentials.h2_mem_creds('dbio-test'),
        # db.io.core.H2Db.new)

    DbIo::Migrators
    .liquibase(@connForge)
    .update('db/io/migration/test-changelog.sql')

    @update_builder = DbIo::UpdateBuilder.new
    .with_conn_factory(@connForge)

    @query = DbIo::QueryBuilder.new
    .with_conn_factory(@connForge)
    .build
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
    @update_builder
    .add_op('insert into db_io.logs (when, msg, level, logger, thread) values (?, ?, ?, ?, ?)',
            Timestamp.new(@now),
            'test msg',
            'DEBUG',
            'test.logger',
            'test.thread')
    .build
    .run

    result = @query.execute(DbIo::LogRecord.java_class, 'select * from db_io.logs')

    refute_equal(result.size, 0)
    assert_equal(result.first.msg, 'test msg')
    assert_equal(result.first.level, 'DEBUG')
    assert_equal(result.first.logger, 'test.logger')
    assert_equal(result.first.thread, 'test.thread')
    assert_equal(result.first.when, Timestamp.new(@now))
    assert_instance_of(Fixnum, result.first.id)
  end
end