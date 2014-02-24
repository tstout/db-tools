gem 'minitest'
require 'minitest/autorun'
require 'java'

java_import 'java.sql.Timestamp'
java_import 'java.util.Calendar'

module DbIo
  include_package 'db.io.operations'
  include_package 'db.io.h2'
end

class TestQuery < MiniTest::Test
  def setup
    @now = Calendar.getInstance.getTimeInMillis
    @creds = DbIo::H2Credentials.h2_local_server_creds("dbio-test", "~/.dbio")
    @db = DbIo::H2Db.new

    @update = DbIo::UpdateBuilder.new
      .with_creds(@creds)
      .with_db(@db)
      .build

    @query = DbIo::QueryBuilder.new
      .with_creds(@creds)
      .with_db(@db)
      .build
  end

  def teardown
    @update.update('delete from db_io.logs')
  end

  # Currently, the java proxy-related code for querying only supports interfaces.
  # Ideally, we should be able to declare a record in Ruby as in the following:
  #
  # LogRecord = Struct.new(:id, :when, :msg, :level, :logger, :thread)
  #
  # I may support this in the near future. For the moment the inteface must
  # be declared in java. I have not yet found a way in Jruby to define a java
  # interface, only implement one.

  def test_basic_read_write_from_ruby
    @update.update('insert into db_io.logs (when, msg, level, logger, thread) values (?, ?, ?, ?, ?)',
                Timestamp.new(@now),
                 "test msg",
                 "DEBUG",
                 "test.logger",
                 "test.thread")

    result = @query.execute(DbIo::LogRecord.java_class, 'select * from db_io.logs')

    refute_equal(result.size, 0)

    result.each do |record|
      puts "id:#{record.id} when:#{record.when} msg: #{record.msg}"
    end
  end

  def test_basic_assertions

    x = %i(a b c)
    assert_instance_of(Array, x)
    assert_equal(x[0], :a)
    assert_equal(x[1], :b)
    assert_equal(x[2], :c)
    refute_nil(x)
  end

end