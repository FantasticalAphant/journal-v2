<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
import type {Entry} from "~/types";

const {data: entries, refresh} = await useFetch<Entry[]>("http://localhost:8080/entries");

async function handleDelete(id: number, journalId: number) {
  console.log(id)
  console.log(journalId)
  await $fetch(`http://localhost:8080/entry/${id}?journalId=${journalId}`, {
    method: "DELETE",
  })

  await refresh();
}

</script>

<template>
  <MainShell
      title="Entries"
      :path="$route.path"
  >
    <EntryListView :entries="entries" :handle-delete="handleDelete"/>

    <pre>{{entries}}</pre>
  </MainShell>
</template>

<style scoped>

</style>