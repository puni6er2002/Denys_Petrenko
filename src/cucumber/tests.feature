Feature: Dropbox functions
  Scenario Outline: User can upload file in Dropbox
    Given User's dropbox
    When User uploads a file with "<path>", "<filename>" and "<content>"
    Then the file has to be uploaded successfully
    Examples:
    | path | filename | content |
    | / | test | My name is Petrenko Denys |

  Scenario Outline: User can get data from some file
    Given User's dropbox
    When User passes "<id>"
    Then the content of file has to be returned
    Examples:
      | id |
    |test|

  Scenario Outline: User can delete file
    Given User's dropbox
    When User deletes file with "<path>" and "<filename>"
    Then the file has to be deleted
    Examples:
      | path | filename |
      | / | test |
