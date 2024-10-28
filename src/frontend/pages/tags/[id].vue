<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
const route = useRoute();
const {data: tag} = await useFetch(`http://localhost:8080/tag/${route.params.id}`);
</script>

<template>
  <MainShell
      title="Tags"
      path="tags">
    <div v-if="tag">
      <h1>Name: {{tag.tagName}}</h1>
      <p>Entries: {{ JSON.stringify(tag.entries) }}</p>
    </div>
  </MainShell>
</template>

<style scoped>

</style>