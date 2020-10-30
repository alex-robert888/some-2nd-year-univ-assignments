#include "ListProblem.h"

ListProblem::ListProblem()
{
	this->list.head = nullptr;
	this->createList();
}

void ListProblem::solve()
{
	this->solveA();
	this->solveB();
}

void ListProblem::printList()
{
	if (this->list.head == nullptr) {
		std::cout << "Empty list";
		return;
	}
	this->printListRec(this->list.head);
}


void ListProblem::printListRec(ListNode* node)
{
	if (node != nullptr) {
		std::cout << node->value << " ";
		printListRec(node->next);
	}
}

/*
	Mathematical model: 
	getLastElementOfList([l1, l2, l3, ..., ln]) = {
		- 0, if l empty
		- l1, if n = 1
		- getLastElementOfList([l2, l3, ..., ln]), otherwise
	}
*/
TElem ListProblem::getLastElementOfList(ListNode* node)
{
	if (node == nullptr) {
		return 0;
	}

	if (node->next == nullptr) {
		return node->value;
	}
	getLastElementOfList(node->next);
}

/*
	Mathematical model:
	deleteFromNtoN(l, i, n) = {
		[], if l empty
		deleteFromNtoN([l2, l3, ..., ln], 1, n), if n = 1
		deleteFromNtoN([l2, l3, ..., ln], 1, n), if i = n - 1
		l1 + deleteFromNtoN([l2, l3, ..., ln], i + 1, n), otherwise
	}
*/
void ListProblem::deleteFromNtoN(ListNode* node, int i, const int n)
{
	// base case
	if (node == nullptr) {
		return;
	}

	// for 1 to 1 case, took this separately because it is the only case when I don't stop right before the element
	if (n == 1) { 
		deleteFromNtoN(node->next, 1, n);
		delete node;
		node = nullptr;
		return;
	}
	
	// in the case that there is one element left in the list and n != 1, 
	// then we know for sure that we won't remove this element,
	// because otherwise the program would have stopped before the node to delete
	if (node->next == nullptr) {
		return;
	}

	// if we got to the position right before the node to delete
	if (i == n - 1) {
		ListNode* nodeToDelete = node->next;
		// if the node to delete is the very last
		if (nodeToDelete->next == nullptr) {
			delete node->next;
			node->next = nullptr;
			return;
		}
		node->next = nodeToDelete->next;
		deleteFromNtoN(nodeToDelete->next, 1, n);
		delete nodeToDelete;
		return;
	}

	deleteFromNtoN(node->next, i + 1, n);
}

void ListProblem::solveA()
{
	std::cout << "\nLast element of the list: " << this->getLastElementOfList(this->list.head);
}


void ListProblem::solveB()
{
	int n;
	std::cout << "\nn: ";
	std::cin >> n;
	if (n <= 0) return;
	deleteFromNtoN(this->list.head, 1, n);
	this->list.head = n == 1 ? nullptr : list.head;
}

void ListProblem::createList()
{
	this->list.head = this->createListRec();
}

ListNode* ListProblem::createListRec()
{
	TElem x;
	std::cout << "value: ";
	std::cin >> x;
	if (x == 0)
		return nullptr;
	else {
		ListNode* p = new ListNode();
		p->value = x;
		p->next = createListRec();
		return p;
	}
}
