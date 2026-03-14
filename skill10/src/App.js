import React, { useState } from "react";

function App() {

  const [students, setStudents] = useState([
    { id: 1, name: "Jashu", course: "CSE" },
    { id: 2, name: "Priya", course: "IT" },
    { id: 3, name: "Rani", course: "ECE" },
    { id: 4, name: "Anjali", course: "EEE" },
    { id: 5, name: "Neha", course: "MECH" }
  ]);

  const [newStudent, setNewStudent] = useState({
    id: "",
    name: "",
    course: ""
  });

  const handleChange = (e) => {
    setNewStudent({
      ...newStudent,
      [e.target.name]: e.target.value
    });
  };

  const addStudent = () => {
    setStudents([...students, newStudent]);
    setNewStudent({ id: "", name: "", course: "" });
  };

  const deleteStudent = (id) => {
    setStudents(students.filter(student => student.id !== id));
  };

  return (
    <div style={{ textAlign: "center" }}>
      <h2>Student Manager</h2>

      <input
        name="id"
        placeholder="ID"
        value={newStudent.id}
        onChange={handleChange}
      />

      <input
        name="name"
        placeholder="Name"
        value={newStudent.name}
        onChange={handleChange}
      />

      <input
        name="course"
        placeholder="Course"
        value={newStudent.course}
        onChange={handleChange}
      />

      <button onClick={addStudent}>Add Student</button>

      <h3>Student List</h3>

      {students.length === 0 ? (
        <p>No students available</p>
      ) : (
        <table border="1" align="center">
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Course</th>
              <th>Action</th>
            </tr>
          </thead>

          <tbody>
            {students.map((student) => (
              <tr key={student.id}>
                <td>{student.id}</td>
                <td>{student.name}</td>
                <td>{student.course}</td>
                <td>
                  <button onClick={() => deleteStudent(student.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>

        </table>
      )}

    </div>
  );
}

export default App;