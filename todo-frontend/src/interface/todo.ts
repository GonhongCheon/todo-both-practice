import { ListResponse } from './response.ts';

export enum TodoStatus {
  DONE = 'DONE',
  PROCEEDING = 'PROCEEDING',
  TODO = 'TODO',
}

export interface Todo {
  content: string;
  id: number;
  status: TodoStatus;
}

export type TodoListResponse = ListResponse<{
  todoList: Todo[];
}>;
