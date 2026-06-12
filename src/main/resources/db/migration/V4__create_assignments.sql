CREATE TABLE assignments (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    deadline TIMESTAMP,
    course_id BIGINT,
    CONSTRAINT fk_assignment_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);