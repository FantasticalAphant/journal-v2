<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";

const route = useRoute()
const journalId = route.params.id
</script>

<template>
  <div>
    <MainShell title="New Entry" path="Entries">
      <EntryTextArea :journalId="journalId"/>
    </MainShell>
  </div>
</template>

<style scoped>

</style>
