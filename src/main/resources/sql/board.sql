ALTER TABLE board 
    CHANGE COLUMN image imageFileName VARCHAR(500) NOT NULL,
    ADD COLUMN imageOriginalFileName VARCHAR(500) NOT NULL AFTER imageFileName;