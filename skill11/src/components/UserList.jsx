import React, { useEffect, useState } from "react";

function UserList() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch("https://jsonplaceholder.typicode.com/users")
      .then(res => res.json())
      .then(data => {
        setUsers(data);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Loading...</p>;

  return (
    <div>
      <h2>API Users</h2>
      {users.map(u => (
        <p key={u.id}>{u.name} | {u.email} | {u.phone}</p>
      ))}
    </div>
  );
}

export default UserList;