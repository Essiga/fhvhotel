Feature: Checking-in a booking
  In order to assign rooms and change the booking status
  As a front office employee
  I want to be able to check-in a booking

  Scenario: Guest wants to check-in, rooms are free
    Given the guest is already created
    And the booking is already created with 1 single and 1 double room and 1 superior room
    And rooms already exist
    And a list of free rooms is already generated
    When I check in the booking
    Then the room status should change from free to occupied for all rooms in the booking
    And the booking status should change from confirmed to checkedin
    And the room is assigned to the correct booking
