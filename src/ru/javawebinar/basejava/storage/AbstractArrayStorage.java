package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected Resume[] storage = new Resume[MAX_SIZE];
    protected int size = 0;
    protected static final int MAX_SIZE = 10000;
    protected static final String RESUME_NOT_EXIST = "Resume %s not found%n";
    protected static final String RESUME_EXIST = "Resume %s already exists%n";
    protected static final String ARRAY_OVERFLOW = "Array overflow - max size:%s%n";

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.printf(RESUME_NOT_EXIST, resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > 0) {
            System.out.printf(RESUME_EXIST, resume.getUuid());
        } else if (size == MAX_SIZE) {
            System.out.printf(ARRAY_OVERFLOW, MAX_SIZE);
        } else {
            insertToPosition(resume, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.printf(RESUME_NOT_EXIST, uuid);
            return;
        }
        removeFromPosition(index);
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.printf(RESUME_NOT_EXIST, uuid);
            return null;
        }
        return storage[index];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertToPosition(Resume resume, int index);

    protected abstract void removeFromPosition(int index);
}