<template>

  <div class="form-feedback">
    <form>
      <h1 class="h3 mb-3 fw-normal">Please give us feedback</h1>
      <div class="mb-3">
        <label for="inputName"></label>
        <input type="text" id="inputName" class="form-control" placeholder="Name" required autofocus
               v-model="feedbacks.name">
      </div>
      <div class="mb-3">
        <label for="inputEmail" class="visually-hidden"></label>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required
               v-model="feedbacks.email">
      </div>
      <div class="mb-3">
        <label for="inputFeedback" class="visually-hidden"></label>
        <textarea id="inputFeedback" class="form-control" placeholder="Text" required
                  v-model="feedbacks.text"></textarea>
      </div>
      <div class="mb-3">
        <label for="inputCategory" class="visually-hidden"></label>
        <select id="inputCategory" class="form-select" multiple aria-label="multiple select example" v-model="options">
          <option value="1" disabled>Application</option>
          <option value="2" disabled>- Health</option>
          <option value="3">-- Patients portal</option>
          <option value="4">-- Doctors portal</option>
          <option value="5" disabled>-- Remote visits portal</option>
          <option value="6">--- Registration</option>
          <option value="7">--- Virtual visit</option>
          <option value="8" disabled>- Document management</option>
          <option value="9">-- OpenKM</option>
          <option value="10">-- Microsoft SharePoint</option>
        </select>
      </div>
      <button class="w-100 btn btn-lg btn-primary" type="submit" @click.prevent="submitForm(); $bvToast.show('toast')">
        Send
      </button>
      <b-toast id="toast" static no-auto-hide v-if="showNotification">
        Your feedback has been sent.
      </b-toast>
      <b-toast id="toast" static no-auto-hide v-else>
        There is something wrong with your submission. Check if the email is correct and that any filed aren't empty.
      </b-toast>
    </form>
  </div>
</template>

<script>
// @ is an alias to /src
import axios from "axios";

export default {
  name: "FeedbackForm",
  data() {
    return {
      feedbacks: {
        name: null,
        email: null,
        categories: null,
        text: null
      },
      options: [],
      showNotification: false
    }
  },
  methods: {
    submitForm: function () {
      // Checking that any fields aren't empty and if the user has chosen  category/categories.
      if (/\S/.test(this.feedbacks.name) && this.validateEmail(this.feedbacks.email) && /\S/.test(this.feedbacks.text) && this.options.length !== 0) {
        axios.post("api/feedbacks", this.feedbacks = { // if the conditions are met then I'll post the data.
          name: this.feedbacks.name,
          email: this.feedbacks.email,
          category: this.options.toString(),
          text: this.feedbacks.text
        })
            .then(function (response) {
              console.log(response);
            })
        this.showNotification = true; // Shows if the feedback was sent correctly for the user.
        window.location.reload() // Gonna reload the page so the table would be updated.
      } else {
        this.showNotification = false;
        console.log("There is something wrong with your submission. Check if the email is correct and that any filed are'nt empty") // If the conditions are not met then give notice to the console.
      }
    },
    validateEmail: function (email) { // Function to check if the given email is formatted correctly
      const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(String(email).toLowerCase());
    }
  },
  props: {
    refresh: Function
  }
};
</script>

<style>
.form-feedback {
  max-width: 350px;
  padding: 50px 15px 15px;
  margin: auto;
}


.form-feedback .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 15px;
}

.form-feedback .form-control:focus {
  z-index: 2;
}

.form-feedback input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-feedback input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}


#inputCategory {
  height: auto;
  width: 320px;
  padding: 20px;
}
</style>