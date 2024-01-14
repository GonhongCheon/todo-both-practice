<script setup lang="ts">
import { onMounted, ref } from 'vue';

import TodoItem from './components/TodoItem/TodoItem.vue';
import http from './http/http.ts';
import { Todo } from './interface';

const content = ref('');
const todos = ref<Todo[]>([]);

const getTodos = async () => {
  todos.value = (await http.get<Todo[]>('/todos'))?.data ?? [];
};

onMounted(() => {
  getTodos();
});

const onSubmit = async () => {
  if (!content.value) return;
  await http.post('/todo', { content: content.value });
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
