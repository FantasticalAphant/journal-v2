<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
import type {Entry} from "~/types";
import {API_URL} from "~/utils/api";

const route = useRoute();
const {data: entry} = await useFetch<Entry>(`${API_URL}/entry/${route.params.id}`);

</script>

<template>
  <MainShell title="Entries" path="entries">
    <EntryTextArea :title="entry.title" :body="entry.body" :id="entry.entryId"/>
    <div v-if="entry">
      <p>Title: {{entry.title}}</p>
      <p>Body: {{entry.body}}</p>
      <p>createdAt: {{ new Date(entry.createdAt).toLocaleString() }}</p>
      <p>modifiedAt: {{ new Date(entry.modifiedAt).toLocaleString() }}</p>
      <p>Journal: {{entry.journalName}}</p>
      <p>Tags: {{entry.tags}}</p>
    </div>
  </MainShell>
</template>

<style scoped>

</style>