#pragma once

#include <iostream>

typedef int TElem;

struct ListNode {
	TElem value;
	ListNode* next;
};

struct List {
	ListNode* head;
};

class ListProblem
{
public: 
	ListProblem();
	void solve();
	void printList();

private:
	List list;

	void solveA();
	void solveB();
	void createList();
	void printListRec(ListNode* node);
	TElem getLastElementOfList(ListNode* node);
	void deleteFromNtoN(ListNode* node, int i, const int n);
	ListNode* createListRec();
};

