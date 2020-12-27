import axios from "axios";
import {
  GET_ERRORS,
  GET_PROJECT,
  GET_PROJECTS,
  DELETE_PROJECT,
} from "../actions/types";
import history from "../history";

export const createProject = (project) => async (dispatch) => {
  try {
    const res = await axios.post("/api/projects", project);
    history.push("/dashboard");
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (err) {
    //console.log("Error: " + err.response.data);
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const getProjects = () => async (dispatch) => {
  try {
    const res = await axios.get("/api/projects");
    dispatch({
      type: GET_PROJECTS,
      payload: res.data,
    });
  } catch (error) {}
};

export const getProject = (id, history) => async (dispatch) => {
  try {
    const res = await axios.get(`/api/projects/${id}`);
    dispatch({
      type: GET_PROJECT,
      payload: res.data,
    });
  } catch (error) {
    history.push("/dashboard");
  }
};

export const deleteProject = (id) => async (dispatch) => {
  try {
    if (
      window.confirm(
        "Are you sure? This will delete the project and all the data related to it!"
      )
    ) {
      await axios.delete(`/api/projects/${id}`);
      dispatch({
        type: DELETE_PROJECT,
        payload: id,
      });
    }
  } catch (error) {}
};
