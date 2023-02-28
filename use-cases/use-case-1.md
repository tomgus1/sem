# USE CASE: 1 Produce a report on population size, from largest to smallest, for a subset of countries

## CHARACTERISTIC INFORMATION

### Goal in Context

As an employee I want to produce the following reports:
* All the countries in the world organised by largest population to smallest.
* All the countries in a continent organised by largest population to smallest.
* All the countries in a region organised by largest population to smallest.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

* We know the countries in the world. 
* We know the countries in a continent.
* We know the countries in a region. [Region to be defined]
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
2. The employee captures the subset (world, the continent, or region) to get population information for.
3. Employee extracts current population of countries of the given subset.
4. Employee provides report to management.

## EXTENSIONS

1. **Subset does not exist**:
    1. Employee informs management that the continent or region does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 2.0