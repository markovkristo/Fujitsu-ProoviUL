<template>
  <div>
    <feedback-table/>
  </div>
</template>

<script>
// @ is an alias to /src
import FeedbackTable from "@/components/FeedbackTable";

export default {
  name: "Home",
  components: {
    "feedback-table": FeedbackTable,
  },
};
</script>

<style>
html,
body {
  height: 100%;
}

body {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-bottom: 40px;
  background-color: #f5f5f5;
}
</style>
