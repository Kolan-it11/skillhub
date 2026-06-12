CREATE TABLE certificates (
    id BIGSERIAL PRIMARY KEY,
    issued_at TIMESTAMP,
    certificate_number VARCHAR(100),
    student_id BIGINT,
    course_id BIGINT,
    CONSTRAINT fk_certificate_student FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_certificate_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);