#!/usr/bin/env ruby

# Add all files in testscripts\SlingRuby\lib directory to ruby "require" search path
require './ruby-lib-dir.rb'

require 'sling/test'
require 'sling/file'
require 'sling/message'
require 'test/unit.rb'
include SlingInterface
include SlingUsers
include SlingMessage
include SlingFile

class TC_ImportIMSCPTest < Test::Unit::TestCase
  include SlingTest
  TEST_SAKAI_DOC_MIME_TYPE = "x-sakai/document"

  def setup
    super
    @ff = FileManager.new(@s)
  end

  def test_import_course
    m = Time.now.to_i.to_s
    @user = create_user("test_user" + m)
    @s.switch_user(@user)
    content = open('../dataload/content-packaging.zip', 'rb') { |f| f.read }
    res = @s.execute_file_post(@s.url_for("/system/pool/importimscp"), "kaka.zip", "kaka.zip", content, "application/zip")
    assert_equal(201, res.code.to_i(), "Expected to be able to upload a zip file, which contains imsmanifest.xml")
    json = JSON.parse(res.body)
    assert_not_nil(json, "Expecting json from course import")
    content = json['_contentItem']["item"]
    mime_type = content["sakai:custom-mimetype"]
    assert_equal(TEST_SAKAI_DOC_MIME_TYPE, mime_type, "Expecting sakai document type from response")
    structure = JSON.parse(content['structure0'])
    # Check whether resources element contains the file information
    structure.each do |item|
      resourceId = structure[item[0]]['_ref'].to_s
      assert_not_nil(content['resources'][resourceId], "Expected there is resource information")
    end
  end
end
