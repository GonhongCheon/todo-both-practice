<script setup lang="ts">
import { onMounted, ref } from "vue";
import http from "./http/http.ts";

const todos = ref([]);

onMounted(async () => {
  todos.value = (await http.get("/todos")) ?? [];
});
</script>

<template>
  <div></div>
</template>

<style scoped></style>
