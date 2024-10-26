<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";

const {data: journals} = await useFetch("http://localhost:8080/journals");
</script>

<template>
  <MainShell>
    <pre>{{journals}}</pre>

    <div v-if="journals">
      <ul>
        <li v-for="journal in journals" :key="journal.journalId">
          Name: {{ journal.name }}
          <br>
          Description: {{ journal.description }}
          <br>
          createdAt: {{ journal.createdAt }}
          <br>
          modifiedAt: {{ journal.modifiedAt }}
          <br>
          journalId: {{ journal.journalId }}
          <br>
          Entries: {{ journal.entries }}
        </li>
      </ul>
    </div>
  </MainShell>
</template>

<style scoped>

</style>