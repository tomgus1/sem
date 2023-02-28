# USE CASE: 5 Produce a report on population size, from largest to smallest, for a subset of capital cities.

## CHARACTERISTIC INFORMATION

### Goal in Context

As an employee I want to produce the following reports:
* All the capital cities in the world organised by largest population to smallest.
* All the capital cities in a continent organised by largest population to smallest.
* All the capital cities in a region organised by largest to smallest.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

* We know the capital cities of the world
* We know the capital cities of each continent
* We know the capital cities of each region [Region to be defined]
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

1. Request population information for a given subset of countries
2. The employee captures the subset (world, the continent, or region) to get population of people living and not living in capital cities
3. Employee extracts current population of people living and not living in capital cities of the given subset.
4. Employee provides report to management.

## EXTENSIONS

1. **Subset does not exist**:
    1. Employee informs management that the capital city does not exist.
    2. Employee informs management that the continent or region does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 2.0