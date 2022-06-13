import { User } from './user';
import { Post } from './post';
export interface Profile {
    user: User;
    posts: Post[];
    followers: User[];
    follows: User[];
}