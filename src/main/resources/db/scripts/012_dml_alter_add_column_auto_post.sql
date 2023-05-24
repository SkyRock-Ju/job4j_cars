ALTER TABLE auto_posts ADD COLUMN IF NOT EXISTS car_id int REFERENCES cars(id);
ALTER TABLE auto_posts ADD COLUMN IF NOT EXISTS file_id int REFERENCES files(id);