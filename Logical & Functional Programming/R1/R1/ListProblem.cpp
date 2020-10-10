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

void ListProblem::deleteFromNtoN(ListNode* node, int i, const int n)
{
	// for 1 to 1
	if (n == 1) { 
		if (node == nullptr)	return;
		deleteFromNtoN(node->next, 1, n);
		delete node;
		node = nullptr;
		return;
	}
	
	if (node->next == nullptr) {
		return;
	}

	if (i == n - 1) {
		ListNode* nodeToDelete = node->next;
		// if the node to delete is the very last
		if (nodeToDelete->next == nullptr) {
			delete node->next;
			node->next = nullptr;
			return;
		}
		node->next = nodeToDelete->next;
		node = nodeToDelete->next;
		delete nodeToDelete;
		deleteFromNtoN(node, 1, n);
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
	std::cout << "n: ";
	std::cin >> n;
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
