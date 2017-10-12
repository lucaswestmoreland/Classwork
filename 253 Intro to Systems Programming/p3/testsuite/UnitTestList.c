/*
 * UnitTestList.c
 *
 *      Author: marissa
 */

#include <stdio.h>
#include <stdlib.h>

#include "Object.h"
#include "Node.h"
#include "List.h"

/*
 * macro to mimic the functionality of assert() from <assert.h>. The difference is that this version doesn't exit the program entirely.
 * It will just break out of the current function (or test case in this context).
 */
#define myassert(expr) if(!(expr)){ fprintf(stderr, "\t[assertion failed] %s: %s\n", __PRETTY_FUNCTION__, __STRING(expr)); return 0; }

struct list *testlist;

int testCount = 0;
int passCount = 0;

void printTestInfo(char* testName, char *info)
{
    fprintf(stdout, "%s - %s\n", testName, info);
}

void printTestResult(char* testName, int passed)
{
    if(passed) {
        fprintf(stdout, "%s - %s\n\n", "[PASSED]", testName);
    } else {
        fprintf(stdout, "%s - %s\n\n", "[FAILED]", testName);
    }
}

struct node *createTestNode(int jobid)
{
    struct object * job = createObject(jobid, "cmd args");
    struct node *node = createNode(job);
    return node;
}

int addAtFrontWithNoNodes()
{
    struct node *node = createTestNode(1);
    addAtFront(testlist, node);
    myassert(testlist->size == 1)
    myassert(testlist->head == node)
    myassert(testlist->tail == node)
    myassert(testlist->head->next == NULL)
    myassert(testlist->head->prev == NULL)
    return 1;
}

int addAtFrontWithOneNode()
{
    struct node *node = createTestNode(1);
	struct node *initial = createTestNode(1);
	addAtFront(testlist, node);
	addAtRear(testlist, initial);
    	myassert(testlist->size == 2)
    	myassert(testlist->head == node)
    	myassert(testlist->tail == initial)
    	myassert(testlist->head->next == initial)
    	myassert(testlist->head->prev == NULL)
	myassert(testlist->tail->prev == node)
    	return 1;
}

int addAtFrontWithTwoNodes()
{
    struct node *node = createTestNode(1);
    struct node *initial = createTestNode(1);
	struct node *initial2 = createTestNode(1);
    addAtFront(testlist, node);
    addAtRear(testlist, initial);
	addAtRear(testlist, initial2);
        myassert(testlist->size == 3)
        myassert(testlist->head == node)
        myassert(testlist->tail == initial2)
        myassert(testlist->head->next == initial)
        myassert(testlist->head->prev == NULL)
    	myassert(testlist->tail->prev == initial)
        return 1;
}


int addAtRearWithNoNodes()
{
    struct node *node = createTestNode(1);
    addAtRear(testlist, node);
   	myassert(testlist->size == 1)
   	myassert(testlist->head == node)
   	myassert(testlist->tail == node)
   	myassert(testlist->head->next == NULL)
  	myassert(testlist->head->prev == NULL)
    	return 1;

}

int addAtRearWithOneNode()
{
    struct node *node = createTestNode(1);
	struct node *initial = createTestNode(1);
	addAtFront(testlist, initial);
	addAtRear(testlist, node);
    	myassert(testlist->size == 2)
    	myassert(testlist->head == initial)
    	myassert(testlist->tail == node)
    	myassert(testlist->head->next == node)
    	myassert(testlist->head->prev == NULL)
		myassert(testlist->tail->prev == initial)
    	return 1;

}

int addAtRearWithTwoNodes()
{
    struct node *node = createTestNode(1);
    struct node *initial = createTestNode(1);
    struct node *initial2 = createTestNode(1);
	addAtFront(testlist, initial);
	addAtFront(testlist, initial2);
    addAtRear(testlist, node);
        myassert(testlist->size == 3)
        myassert(testlist->head == initial2)
        myassert(testlist->tail == node)
        myassert(testlist->head->next == initial)
        myassert(testlist->head->prev == NULL)
        myassert(testlist->tail->prev == initial)
        return 1;

}


int removeFromListWithOneNode()
{
    struct node *node = createTestNode(1);
	addAtFront(testlist, node);
	removeFront(testlist);
	myassert(testlist->size == 0)
	myassert(testlist->head == NULL)
	myassert(testlist->tail == NULL)
	freeNode(node, testlist->freeObject);
	return 1;

}


int removeFromListWithTwoNodes()
{
    struct node *node = createTestNode(1);
	struct node *node2 = createTestNode(1);

    addAtFront(testlist, node);
	addAtFront(testlist, node2);
    removeFront(testlist);
    myassert(testlist->size == 1)
    myassert(testlist->head == node)
    myassert(testlist->tail == node)
    freeNode(node2, testlist->freeObject);
    return 1;

}

int nullNodeTest()
{
	struct node *node = createTestNode(1);
	removeFront(NULL);
	removeRear(NULL);
	removeNode(NULL, NULL);
	removeNode(testlist, NULL);
	removeNode(NULL, node);
	freeNode(node, testlist->freeObject);
	return 1;
}

void beforeTest(char* testName)
{
    printTestInfo(testName, "Running...");
    testlist = createList(equals, toString, freeObject);
    testCount++;
}
void afterTest(char* testName, int result)
{
    printTestResult(testName, result);
    freeList(testlist);
    passCount += result;
}
/*
 * TODO: Write your test functions here
 */


void runUnitTests()
{
    int result;
    char *testName;

    testName = "addAtFrontWithNoNodes";
    beforeTest(testName);
    result = addAtFrontWithNoNodes();
    afterTest(testName, result);

    testName = "addAtFrontWithOneNode";
    beforeTest(testName);
    result = addAtFrontWithOneNode();
    afterTest(testName, result);

	testName = "addAtFrontWithTwoNodes";
    beforeTest(testName);
    result = addAtRearWithTwoNodes();
    afterTest(testName, result);

    testName = "addAtRearWithNoNodes";
    beforeTest(testName);
    result = addAtRearWithNoNodes();
    afterTest(testName, result);

    testName = "addAtRearWithOneNode";
    beforeTest(testName);
    result = addAtRearWithOneNode();
    afterTest(testName, result);

	testName = "addAtRearWithTwoNodes";
    beforeTest(testName);
    result = addAtRearWithTwoNodes();
    afterTest(testName, result);

    testName = "removeFromListWithOneNode";
    beforeTest(testName);
    result = removeFromListWithOneNode();
    afterTest(testName, result);

    testName = "removeFromListWithTwoNodes";
    beforeTest(testName);
    result = removeFromListWithTwoNodes();
    afterTest(testName, result);

    testName = "nullNodeTest";
    beforeTest(testName);
    result = nullNodeTest();
    afterTest(testName, result);


    //TODO: Add in your tests here

    fprintf(stdout, "Test Cases: %d\n",  testCount);
    fprintf(stdout, "Passed: %d\n", passCount);
    fprintf(stdout, "Failed: %d\n", testCount - passCount);
}

int main(int argc, char *argv[])
{
    runUnitTests();
    exit(0);
}
