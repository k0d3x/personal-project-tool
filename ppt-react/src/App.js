import logo from "./logo.svg";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { Router, Route, Switch, Link } from "react-router-dom";
import AddProject from "./components/project/AddProject";
import { Provider } from "react-redux";
import store from "./store/store";
import history from "./history";
import UpdateProject from "./components/project/UpdateProject";

function App() {
  return (
    <Provider store={store}>
      <Router history={history}>
        <div className="App">
          <Header />
          <Switch>
            <Route exact path="/addProject">
              <AddProject />
            </Route>
            <Route
              exact
              path="/updateProject/:id"
              render={(props) => <UpdateProject {...props} />}
            />
            <Route exact path="/dashboard">
              <Dashboard />
            </Route>
          </Switch>
        </div>
      </Router>
    </Provider>
  );
}

export default App;
