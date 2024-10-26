<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";

const {data: entries} = await useFetch("http://localhost:8080/entries");
</script>

<template>
  <MainShell
      title="Entries"
      :path="$route.path"
  >
    <pre>{{entries}}</pre>

    <div v-if="entries">
      <ul>
        <li v-for="entry in entries" :key="entry.entryId">
          Title: {{ entry.title }}
          <br>
          Body: {{ entry.body }}
          <br>
          createdAt: {{ entry.createdAt }}
          <br>
          modifiedAt: {{ entry.modifiedAt }}
          <br>
          Journal: {{ entry.journalName }}
          <br>
          Tags: {{ entry.tags }}
        </li>
      </ul>
    </div>
  </MainShell>
</template>

<style scoped>

</style>