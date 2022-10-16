@tag
Feature: Test Feature 2 for Amphibian Framework
  I want to use this template for my feature file

  @tag2
  Scenario Outline: Validation of Youtube Video
    Given I want to open "<url>" in browser
    Then I verify the "<title>" of video

    Examples: 
      | url  																				| title 	 | 
      | https://www.youtube.com/watch?v=dyoYExlNN10 | Quail & grapes |
