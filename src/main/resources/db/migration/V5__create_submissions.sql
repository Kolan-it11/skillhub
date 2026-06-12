CREATE TABLE submissions (
    id BIGSERIAL PRIMARY KEY,
    content TEXT,
    submitted_at TIMESTAMP,
    grade VARCHAR(10),
    student_id BIGINT,
    assignment_id BIGINT,
    CONSTRAINT fk_submission_student FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_submission_assignment FOREIGN KEY (assignment_id) REFERENCES assignments(id) ON DELETE CASCADE
);