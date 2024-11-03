<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";

const {data: entries} = await useFetch("http://localhost:8080/entries");
</script>

<template>
  <MainShell
      title="Entries"
      :path="$route.path"
  >
    <EntryListView :entries="entries"/>

    <pre>{{entries}}</pre>
  </MainShell>
</template>

<style scoped>

</style>