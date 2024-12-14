<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
import YearViewCalendar from "~/components/YearViewCalendar.vue";
</script>

<template>
  <MainShell
      title="Calendar"
      :path="$route.path"
  >
    <YearViewCalendar/>
  </MainShell>
</template>

<style scoped>

</style>
