# USE CASE: 3 Produce a Report on the Salary of Employees of a Department

## CHARACTERISTIC INFORMATION

### Goal in Context

As a department manager I want to produce a report on the salary of employees in my department so that I can support financial reporting for my department.

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the department.  Database contains current employee salary data.

### Success End Condition

A report is available for the manager to provide to finance.

### Failed End Condition

No report is produced.

### Primary Actor

Manager of a department.

### Trigger

A request for finance information is sent to the manager of a department.

## MAIN SUCCESS SCENARIO

1. Finance request salary information for the manager's department.
2. Manager extracts current salary information of all employees of their department.
3. Manager provides report to finance.

## EXTENSIONS

3. **Department does not exist**:
    1. Manager informs finance the department does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 2.0