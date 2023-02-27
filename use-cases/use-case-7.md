## USE CASE 9: Get Top N Populated Capital Cities in a Region

## CHARACTERISTIC INFORMATION

### Goal in Context
As a user, I want to be able to retrieve the top N populated capital cities in a region, where N is provided by me.

## Scope
Geographic regions.

## Level
Primary task.

## Preconditions
The user has access to the system and has provided a valid value of N.

## Success End Condition
The system displays the top N populated capital cities in the selected region.

## Failed End Condition
The system is unable to display the top N populated capital cities in the selected region.

## Primary Actor
User.

## Trigger
A request to retrieve the top N populated capital cities in a region is received.

## MAIN SUCCESS SCENARIO
User enters the region for which they want to retrieve the top N populated capital cities.
User provides a value for N.
System retrieves the top N populated capital cities in the selected region.
System displays the retrieved cities to the user.

## EXTENSIONS
The provided region is invalid or does not exist.
System provides an error message explaining why the cities cannot be retrieved.
## SUB-VARIATIONS
None.
## SCHEDULE
## DUE DATE: Release 1.0

