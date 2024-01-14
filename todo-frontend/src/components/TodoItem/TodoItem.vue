<script setup lang="ts">
import { computed, ref } from 'vue';

import http from '../../http/http.ts';
import { Todo } from '../../interface';

const emit = defineEmits(['delete', 'update']);

const props = defineProps<{
  todo: Todo;
}>();

const todo = computed(() => props.todo);
const mode = ref('read');
const content = ref(todo.value.content);

const toggleMode = () => {
  mode.value = mode.value === 'read' ? 'update' : 'read';
};

const onClickUpdate = async (id: number) => {
  if (mode.value === 'update') {
    await http.put(`/todo/${id}`, { content: content.value });
    emit('update');
  }
  toggleMode();
};

const onClickDelete = async (id: number) => {
  await http.delete(`/todo/${id}`);
  emit('delete');
};

const modeButtonText = computed(() => (mode.value === 'read' ? 'UPDATE' : 'DONE'));
</script>

<template>
  <div>
    <p v-if="mode === 'read'">{{ todo.content }}</p>
    <input v-if="mode === 'update'" v-model="content" type="text" />
    <button @click="onClickUpdate(todo.id)">{{ modeButtonText }}</button>
    <button @click="onClickDelete(todo.id)">DELETE</button>
  </div>
</template>

<style scoped></style>
