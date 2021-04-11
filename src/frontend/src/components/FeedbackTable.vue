<template>
  <div class="feedback-table-area">
    <table class="feedback-table" aria-label="Feedback table">
      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Email</th>
        <th scope="col">Category</th>
        <th scope="col">Text</th>
      </tr>
      </thead>
      <tbody>
      <!-- I'm using v-for to display the contents of feedbacks. -->
      <tr v-for="feedback in feedbacks" :key="feedback.id">
        <th scope="row">{{ feedback.id }}</th>
        <td>{{ feedback.name }}</td>
        <td>{{ feedback.email }}</td>
        <td>{{ feedback.category }}</td>
        <td>{{ feedback.text }}</td>
      </tr>
      </tbody>
    </table>
    <div>
      <FeedbackForm :refresh="getFeedbacks"/>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import FeedbackForm from "@/components/FeedbackForm";

export default {
  name: 'FeedbackTable',
  components: {
    FeedbackForm
  },
  data() {
    return {
      feedbacks: []
    }
  },
  methods: {
    getFeedbacks: function () {
      axios.get("api/feedbacks") // Getting data
          .then(resp => {
            this.feedbacks = resp.data // Giving the data to the feedbacks array.
          })
          .catch(error => {
            console.log(error);
          });
    }
  },
  mounted() {
    this.getFeedbacks();
  },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.feedback-table-area, .feedback-table-area .feedback-table {
  width: 100%;
}

</style>
