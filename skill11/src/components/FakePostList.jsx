import React, { useEffect, useState } from "react";
import axios from "axios";

function FakePostList() {
  const [posts, setPosts] = useState([]);
  const [filter, setFilter] = useState("");

  const fetchData = () => {
    axios.get("https://dummyjson.com/posts")
      .then(res => setPosts(res.data.posts))
      .catch(err => console.log(err));
  };

  useEffect(() => {
    fetchData();
  }, []);

  const filteredPosts = filter
    ? posts.filter(p => p.userId === Number(filter))
    : posts;

  return (
    <div>
      <h2>Fake API Posts</h2>

      <select onChange={(e) => setFilter(e.target.value)}>
        <option value="">All</option>
        <option value="1">User 1</option>
        <option value="2">User 2</option>
      </select>

      <button onClick={fetchData}>Refresh</button>

      {filteredPosts.map(p => (
        <div key={p.id}>
          <h4>{p.title}</h4>
          <p>{p.body}</p>
        </div>
      ))}
    </div>
  );
}

export default FakePostList;