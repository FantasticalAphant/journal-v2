<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
import type {Tag} from "~/types";
import {API_URL} from "~/utils/api";

const route = useRoute();
const {data: tag} = await useFetch<Tag>(`${API_URL}/tag/${route.params.id}`);
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