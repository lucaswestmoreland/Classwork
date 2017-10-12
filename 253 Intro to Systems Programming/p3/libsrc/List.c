#include <stdio.h>
#include <stdlib.h>
#include "List.h"
#include "Node.h"

struct list * createList(int (*equals)(const void *,const void *),
				char * (*toString)(const void *),
				void (*freeObject)(void *))
{
		struct list *list;
		list = (struct list *) malloc(sizeof(struct list));
		list->size = 0;
		list->head = NULL;
		list->tail = NULL;
		list->equals = equals;
		list->toString = toString;
		list->freeObject = freeObject;
		return list;
}

void freeList(struct list *list)
{

		if (list == NULL)
				return;

		struct node* current = list->head;

		void *temp;         
		while(current != NULL){  
				temp = current->next;
				freeNode(current, list->freeObject);
				current = temp;
		}
		free(list);



}

int getSize(const struct list *list)
{
		return list->size;
}

int isEmpty(const struct list *list)
{
		return list == NULL || list->size == 0;
}

void addAtFront(struct list *list, struct node *node)
{
		if (list == NULL) 
				return;
		if (node == NULL) 
				return;
		list->size++;
		node->next = list->head;
		node->prev = NULL;
		if(list->size==0) {
				list->head = node;
				list->tail = node;
				list->head->next = NULL;
				list->head->prev = NULL;
		}

		if (list->head == NULL) {
				list->head = node;
				list->tail = node;
		} else {
				list->head->prev = node;
				list->head = node;
		}
}

void addAtRear(struct list *list, struct node *node)
{
		if(list==NULL) 
				return;
		if(node==NULL)
				return;
		list->size++;
		node->prev = list->tail;
		node->next = NULL;
		if(list->size==0 || list->tail == NULL) {
				list->head = node;
				list->tail = node;
				list->head->next = NULL;
				list->head->prev = NULL;
		}
		else{
				list->tail->next = node;
				list->tail = node;
		}

}

struct node* removeFront(struct list *list)
{
		if(list==NULL){
				return NULL;
		}
		if(list->size == 0){
				return NULL;
		}

		struct node* temp = list->head;
		if(list->size == 1){
				list->head = NULL;
				list->tail = NULL;
		}

		else{
				list->head = list->head->next;
				list->head->prev = NULL;
		}

		temp->next = temp->prev = NULL;
		list->size--;
		return temp;
}

struct node* removeRear(struct list *list)
{
		if(list==NULL){
				return NULL;
		}
		if(list->size == 0){
				return NULL;
		}
		struct node* temp = list->tail;
		if(list->size == 1){
				list->tail = NULL;
				list->head = NULL;
		}

		else{
				list->tail = list->tail->prev;
				list->tail->next = NULL;
		}
		temp->next = temp->prev = NULL;
		list->size--;
		return temp;
}

struct node* removeNode(struct list *list, struct node *node)
{
		if(list==NULL){
				return NULL;
		}
		if(node==NULL){
				return NULL;
		}
		if(list->size == 0){
				return NULL;
		}
		if(list->size == 1){
				list->head = list->tail = NULL;
				node->next = NULL;
				node->prev = NULL;
				list->size--;
				return node;
		}

		else if(node == list->tail){
				return removeRear(list);
		}

		else if(node == list->head){
				return removeFront(list);
		}
		else{
				node->prev->next = node->next;
				node->next->prev = node->prev;
				node->next = NULL;
				node->prev = NULL;
				list->size--;
				return node;
		}
}

struct node* search(const struct list *list, const void *obj)
{
		struct node* current = list->head;
		while(current){
				if(current == obj){
						return current;
				}
				current = current->next;
		}
return 0;
}

void reverseList(struct list *list)
{
		struct node* head = list->head;
		struct node* current = list->head;
		struct node* temp = current;

		while(current){
				temp = current->next;
				current->next = current->prev;
				current->prev = temp;
				temp = current;
				current = current->prev;
		}
		list->head = list->tail;
		list->tail = head;
}

void printList(const struct list *list)
{
		if (!list) return;
		int count = 0;
		char *out;
		struct node* temp = list->head;
		while (temp) {
				out = list->toString(temp->obj);
				printf(" %s -->",out);
				free(out);
				temp = temp->next;
				count++;
				if ((count % 6) == 0)
						printf("\n");
		}
		printf(" NULL \n");
}
