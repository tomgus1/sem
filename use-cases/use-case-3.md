# USE CASE: 3 Produce a report on population size, from largest to smallest, for a subset of cities

## CHARACTERISTIC INFORMATION

### Goal in Context

As an employee I want to produce the following reports:
* All the cities in the world organised by largest population to smallest.
* All the cities in a continent organised by largest population to smallest. 
* All the cities in a region organised by largest population to smallest. 
* All the cities in a country organised by largest population to smallest. 
* All the cities in a district organised by largest population to smallest.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

* We know the cities in the world.
* We know the cities in a continent.
* We know the cities in a region. [Region to be defined]
* We know the cities in a country.
* We know the cities in a district.
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

1. Request population information for a given subset of cities
2. The employee captures the subset (world, the continent, region, country, or district) to get population information for.
3. Employee extracts current population of cities of the given subset.
4. Employee provides report to management.

## EXTENSIONS

1. **Subset does not exist**:
   1. Employee informs management that the continent, region, country or district does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 2.0