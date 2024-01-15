<script setup lang="ts">
import { onMounted, ref } from 'vue';

import TodoItem from './components/TodoItem/TodoItem.vue';
import http from './http/http.ts';
import { Todo, TodoListResponse, TodoStatus } from './interface';

const content = ref('');
const todos = ref<Todo[]>([]);

const getTodos = async () => {
  const res = (await http.get<TodoListResponse>('/todos'))?.data;
  const { todoList } = res?._embedded ?? {};
  todos.value = todoList ?? [];
};

onMounted(() => {
  getTodos();
});

const onSubmit = async () => {
  if (!content.value) return;
  await http.post<Todo, Todo>('/todo', { content: content.value, status: TodoStatus.TODO });
  await getTodos();
  content.value = '';
};
</script>

<template>
  <form @submit.prevent="onSubmit">
    <input v-model="content" type="text" name="content" />
    <button type="submit">ADD</button>
  </form>
  <ul>
    <li v-for="todo of todos" :key="todo.id">
      <TodoItem :todo="todo" @update="getTodos" @delete="getTodos" />
    </li>
  </ul>
</template>

<style scoped></style>
