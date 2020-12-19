import React, { Component } from "react";
import { getProject, createProject } from "../../actions/projectActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import classnames from "classnames";

class UpdateProject extends Component {
  constructor() {
    super();
    this.state = {
      id: "",
      projectName: "",
      projectIdentifier: "",
      description: "",
      startDate: "",
      endDate: "",
      errorMessages: "",
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors.errorMessages) {
      this.setState({ errorMessages: nextProps.errors.errorMessages });
    } else if (nextProps.project) {
      console.log(nextProps.project);
      this.setState({
        id: nextProps.project.id,
        projectName: nextProps.project.projectName,
        projectIdentifier: nextProps.project.projectIdentifier,
        description: nextProps.project.description,
        startDate: nextProps.project.startDate,
        endDate: nextProps.project.endDate,
      });
    }
  }

  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getProject(id, this.props.history);
  }

  onChange(event) {
    this.setState({
      [event.target.name]: event.target.value,
    });
  }
  onSubmit(event) {
    event.preventDefault();
    const updateProject = {
      id: this.state.id,
      projectName: this.state.projectName,
      projectIdentifier: this.state.projectIdentifier,
      description: this.state.description,
      startDate: this.state.startDate,
      endDate: this.state.endDate,
    };
    this.props.createProject(updateProject, this.props.history);
  }
  render() {
    const { errorMessages } = this.state;
    return (
      <div>
        <div className="project">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Update Project</h5>
                <hr />
                <form method="post" onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg ", {
                        "is-invalid": errorMessages.projectName,
                      })}
                      placeholder="Project Name"
                      name="projectName"
                      value={this.state.projectName || ""}
                      onChange={this.onChange}
                    />
                    {errorMessages.projectName && (
                      <div className="invalid-feedback">
                        {errorMessages.projectName}
                      </div>
                    )}
                  </div>

                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg "
                      placeholder="Unique Project ID"
                      name="projectIdentifier"
                      value={this.state.projectIdentifier || ""}
                      disabled
                    />
                  </div>
                  <div className="form-group">
                    <textarea
                      className={classnames("form-control form-control-lg ", {
                        "is-invalid": errorMessages.description,
                      })}
                      placeholder="Project Description"
                      name="description"
                      value={this.state.description || ""}
                      onChange={this.onChange}
                    ></textarea>
                    {errorMessages.description && (
                      <div className="invalid-feedback">
                        {errorMessages.description}
                      </div>
                    )}
                  </div>
                  <h6>Start Date</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="startDate"
                      value={this.state.startDate || ""}
                      onChange={this.onChange}
                    />
                  </div>
                  <h6>Estimated End Date</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="endDate"
                      value={this.state.endDate || ""}
                      onChange={this.onChange}
                    />
                  </div>

                  <input
                    type="submit"
                    className="btn btn-primary btn-block mt-4"
                  />
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

UpdateProject.propTypes = {
  getProject: PropTypes.func.isRequired,
  project: PropTypes.object.isRequired,
  createProject: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  project: state.project.project,
  errors: state.errors,
});
export default connect(mapStateToProps, { getProject, createProject })(
  UpdateProject
);
