<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
import type {Tag} from "~/types";
import {API_URL} from "~/utils/api";

const {data: tags, refresh} = await useFetch<Tag[]>(`${API_URL}/tags`);

const tagName = ref("");

async function handleSubmit(event: Event) {
  event.preventDefault();

  await $fetch(`${API_URL}/tags`, {
    method: "POST",
    body: {
      tagName: tagName.value,
    }
  });

  tagName.value = "";
  await refresh();
}

</script>

<template>
  <MainShell
      title="Tags"
      :path="$route.path">
    <form @submit="handleSubmit" class="flex max-w-xs gap-2 mb-3">
      <input v-model="tagName" placeholder="Name" class="pl-1 border-2 rounded">
      <button type="submit" class="bg-blue-500 text-white p-1 rounded">Submit</button>
    </form>
    <TagsListView :tags="tags"/>
  </MainShell>
</template>

<style scoped>

</style>