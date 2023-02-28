# USE CASE: 2 Produce a report on population size, for the top N countries, for a subset of countries

## CHARACTERISTIC INFORMATION

### Goal in Context

As an employee I want to produce the following reports:
* The top N populated countries in the world where N is provided by the user.
* The top N populated countries in a continent where N is provided by the user.
* The top N populated countries in a region where N is provided by the user.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

* We know the countries in the world.
* We know the countries in a continent.
* We know the countries in a region. [Region to be defined]
* We know 'N'.
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

1. Request population information for a given subset of countries, limit N
2. The employee captures the subset (world, the continent, or region) to get population information for.
3. Employee extracts current population of countries of the given subset with limit N.
4. Employee provides report to management.

## EXTENSIONS

1. **Subset does not exist**:
   1. Employee informs management that the continent or region does not exist.

## SUB-VARIATIONS

If 'N' is larger than the list of countries in the subset, then the query shouldn't fail, it should just return the full list.

## SCHEDULE

**DUE DATE**: Release 2.0