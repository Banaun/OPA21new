// The html tags
let headerTag = document.querySelector("#page-header");
let formContainer = document.querySelector("#form-container");
let allUsersContainer = document.querySelector("#all-users-container");
let loggedIn = false;
onhashchange = changePage; // vid varje ändring av hash i URL:en
changePage(); // vid inladdning av sidan
function changePage() {
  let page = location.hash.replace("#", "");
  console.log("redirected to page: " + page);

  switch (page) {
    case "create-account":
      renderCreateAccountPage();
      const createAccountForm = document.querySelector("#form-create-account");
      createAccountForm.addEventListener("submit", createAccount);
      break;
    case "profile-settings":
      if (loggedIn == true) {
        renderProfileSettingsPage();
        const profileSettingsForm = document.querySelector("#form-profile-settings");
        profileSettingsForm.addEventListener("submit", changePassword);
        break;
      }
      else {
        goToPage("/");
        alert("You need to be logged in to access the profile settings.");
        break;
      }
    default:
      // If we are not currently in either create-account or profile-settings
      // That means we are at the startpage thus renderLoginPage
      renderLoginPage();
      const loginForm = document.querySelector("#form-login");
      loginForm.addEventListener("submit", authenticateUser);
  }
}

function goToPage(pageToGo) {
  location.href = pageToGo;
}

let authUsername; // here we will save the logged in username
let authPassword; // here we will save the logged in user's password
let users = [];

// TODO: JAVA EXPRESS REQUEST 1
async function authenticateUser(event) {
  event.preventDefault();
  // Write request to Java Express server to get a response back if login was successful
  // If YES, change page to "#profile-settings" with:   goToPage("/#profile-settings");
  //    Also, store the username and password to the variables declared above this function in order for the program to remember them
  // If NO, maybe use an:                               alert("Insert Server Message response here");
  let uname = document.getElementById("username").value;
  let pwd = document.getElementById("password").value;
  await getAllUsers();

  if (uname == '') {
    alert("Please enter a username.");
  }
  else if (pwd == '') {
    alert("Please enter a password.")
  }
  else {
    for (let user of users) {
      if (uname == user.username && pwd == user.password) {
        authUsername = uname;
        authPassword = pwd;
        loggedIn = true;
        alert("Login successful.");
        goToPage("/#profile-settings");
        return;
      }
    }
    alert("Wrong username and/or password.");
    document.getElementById("form-login").reset();
    console.log(uname + ' ' + pwd);
  }
}

// TODO: JAVA EXPRESS REQUEST 2
async function createAccount(event) {
  event.preventDefault();
  // Write request to Java Express server to create new account
  // If account already exists and request failed, alert the user of this
  // If account was successfully created, change page to:  location.href = "/#profile-settings";

  console.log("createAccount clicked");
  let uname = document.getElementById("username").value;
  let pwd = document.getElementById("password").value;
  let continueWithCreation = true;

  await getAllUsers();

  if (uname == '') {
    alert("Please enter a username.");
    continueWithCreation = false;
  }
  else if (pwd == '') {
    alert("Please enter a password.");
    continueWithCreation = false;
  }
  else if (pwd.length < 5) {
    alert("Password needs 5 or more characters.")
    document.getElementById("form-create-account").reset();
    continueWithCreation = false;
  }
  else {
    for (let user of users) {
      if (uname == user.username) {
        alert("Username is already taken.");
        document.getElementById("form-create-account").reset();
        continueWithCreation = false;
      }
    }
  }

  if (continueWithCreation == true) {
    let user = {
      username: uname,
      password: pwd
    };
  
    let result = await fetch("/rest/users", {
      method: "POST",
      body: JSON.stringify(user)
    });

    console.log(await result.text());
    loggedIn = true;
    goToPage("/#profile-settings");
  }
}

function logOut() {
  authUsername = null;
  authPassword = null;
  loggedIn = false;
}

// TODO: JAVA EXPRESS REQUEST 3
async function changePassword(event) {
  event.preventDefault();
  // Write request to Java Express server to change current user's password in the database
  // Use an:    alert("Password successfully changed")

  console.log("change password event");

  let pwd = document.getElementById("password").value;
  let newPwd = document.getElementById("new-password").value;

  if (pwd == authPassword) {
    if (newPwd.length < 5) {
      alert("New password must be 5 characters or more.")
      return;
    }
    else {
      let user = {
        username: authUsername,
        password: newPwd
      };

      let result = await fetch("/rest/users", {
        method: "PUT",
        body: JSON.stringify(user)
      });
      alert("Password has been updated.");
      authPassword = newPwd;
      console.log(authPassword);
      document.getElementById("form-profile-settings").reset();
    }
  }
  else {
    alert("Wrong password!");
    document.getElementById("form-profile-settings").reset();
  }
}

// TODO: JAVA EXPRESS REQUEST 4
async function deleteAccount() {
  // Delete the account you are currently logged in as through sending a request to Java Express Server

  console.log("Delete account clicked");

  let user = {
    username: authUsername,
    password: authPassword
  };

  let result = await fetch("/rest/users", {
    method: "DELETE",
    body: JSON.stringify(user)
  });

  alert("Account deleted.");
  loggedIn = false;
  goToPage("/");
}

// TODO: JAVA EXPRESS REQUEST 5
async function peekAllUsers() {
  // Send a request to the java express server to retrieve all the users from the database as JSON
  // Then try to make them appear in the Profile-Settings page inside the '<section id="all-users-container"></section>'

  console.log("Peek All Users clicked");
  await getAllUsers();
  renderUsers();
}

async function getAllUsers() {
  let result = await fetch("/rest/users");
  users = await result.json();

  console.log(users);
}

function renderUsers() {

  allUsersContainer.innerHTML = `
    <h2>
      Users in the database:
    </h2>
  `;

  for(let user of users) {
    let userLi = `
      <li class="user-list-object">
        username: ${user.username} <br>
        password: ${user.password} <br>  
      </id><br>
    `
    allUsersContainer.innerHTML += userLi;
  }
  allUsersContainer.innerHTML += `
      <button id="btn-hide" onclick="renderProfileSettingsPage()">HIDE</button>
    `;
}

function renderLoginPage() {
  headerTag.innerHTML = "<h2>Login Page</h2>";
  formContainer.innerHTML = `
    <form id="form-login">
        <label for="username">Username</label><br />
        <input id="username" type="text" /><br />
        <label for="password">Password</label><br />
        <input id="password" type="password" /><br />
        <button type="submit">Login</button>
    </form>
    <button id="btn-go-to-create-account" onclick="goToPage('/#create-account')"> Don't have an account yet? Click here to register</button>
  `;
}

function renderCreateAccountPage() {
  headerTag.innerHTML = "<h2>Create Account</h2>";
  formContainer.innerHTML = `
    <form id="form-create-account">
      <label for="username">New Username</label><br />
      <input id="username" type="text" /><br />
      <label for="password">New Password</label><br />
      <input id="password" type="password" /><br />
      <button type="submit">Create account</button>
    </form>
    <button id="btn-go-to-create-account" onclick="goToPage('/')">Back to login page</button>
  `;
}

function renderProfileSettingsPage() {
  headerTag.innerHTML = "<h2>Profile Settings</h2>";
  formContainer.innerHTML = `
      <h2>Hello ${authUsername}! This is your settings page.</h2>
      <form id="form-profile-settings">
        <label for="password">Enter previous password</label><br />
        <input id="password" type="password" /><br />
        <label for="new-password">Enter new password</label><br />
        <input id="new-password" type="password" /><br />
        <button type="submit">Change Password</button>
      </form>
      <div id="menu-buttons">
        <button id="btn-logout" onclick="logOut()"><a href="/">Logout</a></button>
        <button id="btn-delete" onclick="deleteAccount()">Delete Account</button>
      </div>
  `;
  allUsersContainer.innerHTML = `
  <h2>
    Click this button below to take a sneek peek on the other users...
  </h2>
  <button id="btn-peek" onclick="peekAllUsers()">PEEK</button>
`;
}
