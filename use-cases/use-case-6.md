## USE CASE: 7 Produce a report on population size, from largest to smallest, for the top N capital cities, for a subset of capital cities

## CHARACTERISTIC INFORMATION

### Goal in Context

As an employee I want to produce the following reports:
* The top N populated capital cities in the world where N is provided by the user.
* The top N populated capital cities in a continent where N is provided by the user.
* The top N populated capital cities in a region where N is provided by the user.

### Scope

Company.

### Level

Primary task.

### Preconditions

* We know the capital cities of the world
* We know the capital cities of each continent
* We know the capital cities of each region [Region to be defined]
* We know 'N'
* Database contains current population data.

### Success End Condition

A report is available for employees to provide when requested by management.

### Failed End Condition

No report is produced.

### Primary Actor

Management.

### Trigger

A request for a report is sent to an employee.

## MAIN SUCCESS SCENARIO

1. Request population information for a given subset of capital cities, limit N
2. The employee captures the subset (world, the continent, or region) to get population information for.
3. Employee extracts current population of capital cities of the given subset with limit N.
4. Employee provides report to management.

## EXTENSIONS

1. **Subset does not exist**:
   1. Employee informs management that the capital city does not exist.
   2. Employee informs management that the continent or region does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 2.0