<script setup lang="ts">
import { computed, ref } from 'vue';

import http from '../../http/http.ts';
import { Todo, TodoStatus } from '../../interface';

const emit = defineEmits(['delete', 'update']);

const props = defineProps<{
  todo: Todo;
}>();

const todo = computed(() => props.todo);
const mode = ref('read');
const status = ref(todo.value.status);
const content = ref(todo.value.content);

const toggleMode = () => {
  mode.value = mode.value === 'read' ? 'update' : 'read';
};

const onClickUpdate = async (id: number) => {
  if (mode.value === 'update') {
    await http.put<Todo, Todo>(`/todo/${id}`, { content: content.value, status: status.value });
    emit('update');
  }
  toggleMode();
};

const onClickDelete = async (id: number) => {
  await http.delete(`/todo/${id}`);
  emit('delete');
};

const onClickTodoStatus = () => {
  switch (status.value) {
    case TodoStatus.TODO:
      status.value = TodoStatus.PROCEEDING;
      return;
    case TodoStatus.PROCEEDING:
      status.value = TodoStatus.DONE;
      return;
    case TodoStatus.DONE:
      status.value = TodoStatus.TODO;
      return;
    default:
      return;
  }
};

const modeButtonText = computed(() => (mode.value === 'read' ? 'UPDATE' : 'DONE'));
</script>

<template>
  <div>
    <p v-if="mode === 'read'">{{ todo.content }}</p>
    <input v-if="mode === 'update'" v-model="content" type="text" />
    <button v-if="mode === 'update'" @click="onClickTodoStatus">{{ status }}</button>
    <button @click="onClickUpdate(todo.id)">{{ modeButtonText }}</button>
    <button @click="onClickDelete(todo.id)">DELETE</button>
  </div>
</template>

<style scoped></style>
