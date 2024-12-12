<script setup lang="ts">
import _ from "lodash"
import type {Entry} from "~/types";

const props = withDefaults(defineProps<{
  id?: string;
  title?: string;
  body?: string;
  journalId?: string;
}>(), {
  title: "",
  body: "",
})

const title = ref(props.title)
const content = ref(props.body)
const journalId = ref(props.journalId)
const wordCount = ref(0)
const hasUnsavedChanges = ref(false)
const lastSaved = ref(new Date())
const lastSavedContent = ref("")
const saveStatus = ref("")

const route = useRoute()
const {coords} = useGeolocation()

const saveEntry = async (text: string) => {
  // TODO: Use more robust path checking
  // TODO: Also update the entry when the title is changed
  switch(true) {
    case route.path.startsWith("/entries/create"):
      const response: Entry = await $fetch(`http://localhost:8080/journals/${journalId.value}`, {
        method: "POST",
        body: {
          title: title.value,
          body: content.value,
          latitude: coords.value.latitude,
          longitude: coords.value.longitude,
        }
      })

      const entryId = response.entryId;
      navigateTo(`/entries/${entryId}`, {
        replace: true,
        external: false
      })
      break;
    case route.path.startsWith("/entries"):
      await $fetch(`http://localhost:8080/entry/${props.id}`, {
        method: "POST",
        body: {
          title: title.value,
          body: content.value,
        }
      })
      break;
    default:
      console.error("This shouldn't be happening");
  }
}

const debouncedSave = _.debounce(async (text) => {
  if (text === lastSavedContent.value) {
    return; // only update if text has changed
  }

  saveStatus.value = "Saving..."

  try {
    await saveEntry(text)
    lastSaved.value = new Date()
    hasUnsavedChanges.value = false
    lastSavedContent.value = text
    saveStatus.value = "Saved"
  } catch (error) {
    saveStatus.value = "Failed to save"
  }
}, 1500)

watch(content, (newContent) => {
  if (newContent !== lastSavedContent.value) {
    hasUnsavedChanges.value = true
    debouncedSave(newContent)
  }
})

watch(content, () => {
  wordCount.value = content.value.trim() ? content.value.trim().split(/\s+/).length : 0
}, {immediate: true})
</script>

<template>
  <div class="flex justify-between -mt-6">
    {{wordCount}} Words
    [{{saveStatus}}]
    <NuxtLink to="/entries" class="bg-amber-500 rounded px-1 shadow">Back to all entries</NuxtLink>
  </div>
  <input v-model="title" type="text" class="block w-full border-0 h-10 my-3 pl-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600" placeholder="Entry Title" />
  <textarea
      v-model="content"
      name="entry"
      id="entry"
      placeholder="Entry Text"
      class="block w-full h-96 rounded-md text-xl border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 pl-2 resize-none"/>
</template>