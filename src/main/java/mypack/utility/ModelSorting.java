package mypack.utility;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import mypack.model.Post_;
import mypack.model.Profile_;
import mypack.model.Report_;

public class ModelSorting {
	/**
	 * Sort by combination of selected attribute <br>
	 * <i>Example: sortBy = (1+2+4) = 7 => sort by recruit, salary and createDate
	 * attribute
	 * 
	 * @param sortBy         null for unsorted and possible value is 1, 2, 4, 8
	 * @param sortDescending true if sort in descending order, otherwise ascending
	 * @return Sort object <br>
	 *         sortBy possible value <br>
	 *         &nbsp;&nbsp;&nbsp;&nbsp; 1: by createDate - newest post <br>
	 *         &nbsp;&nbsp;&nbsp;&nbsp; 2: by salary <br>
	 *         &nbsp;&nbsp;&nbsp;&nbsp; 4: by number of recruit <br>
	 *         &nbsp;&nbsp;&nbsp;&nbsp; 8: by number of viewcount <br>
	 */
	public static Sort getPostSort(Integer sortBy, Boolean sortDescending) {
		Sort sort = Sort.unsorted();

		if (sortBy != null) {
			if (sortDescending == null || !sortDescending.booleanValue()) { // ASC
				if (sortBy >= 16) {
					sort = sort.and(JpaSort.of(Post_.acceptedDate).ascending());
					sortBy -= 16;
				}
				if (sortBy >= 8) {
					sort = sort.and(JpaSort.of(Post_.viewCount).ascending());
					sortBy -= 8;
				}
				if (sortBy >= 4) {
					sort = sort.and(JpaSort.of(Post_.recruit).ascending());
					sortBy -= 4;
				}
				if (sortBy >= 2) {
					sort = sort.and(JpaSort.of(Post_.salary).ascending());
					sortBy -= 2;
				}
				if (sortBy >= 1) {
					sort = sort.and(JpaSort.of(Post_.createDate).ascending());
					sortBy -= 1;
				}
			} else { // DESC
				if (sortBy >= 16) {
					sort = sort.and(JpaSort.of(Post_.acceptedDate).descending());
					sortBy -= 16;
				}
				if (sortBy >= 8) {
					sort = sort.and(JpaSort.of(Post_.viewCount).descending());
					sortBy -= 8;
				}
				if (sortBy >= 4) {
					sort = sort.and(JpaSort.of(Post_.recruit).descending());
					sortBy -= 4;
				}
				if (sortBy >= 2) {
					sort = sort.and(JpaSort.of(Post_.salary).descending());
					sortBy -= 2;
				}
				if (sortBy >= 1) {
					sort = sort.and(JpaSort.of(Post_.createDate).descending());
					sortBy -= 1;
				}
			}
		}
		return sort;
	}

	/*
	 * 1 -> Experience
	 * 2 -> Method
	 * 3 -> job position
	 * 4 -> name
	 */
	public static Sort getProfileSort(Integer sortBy, Boolean sortDescending) {
		Sort sort = Sort.unsorted();

		if (sortBy != null) {
			if (sortDescending == null || !sortDescending.booleanValue()) { // ASC
				if (sortBy >= 8) {
					sort = sort.and(JpaSort.of(Profile_.name).ascending());
					sortBy -= 8;
				}
				if (sortBy >= 4) {
					sort = sort.and(JpaSort.of(Profile_.position).ascending());
					sortBy -= 4;
				}
				if (sortBy >= 2) {
					sort = sort.and(JpaSort.of(Profile_.method).ascending());
					sortBy -= 2;
				}
				if (sortBy >= 1) {
					sort = sort.and(JpaSort.of(Profile_.experience).ascending());
					sortBy -= 1;
				}
			} else { // DESC
				if (sortBy >= 8) {
					sort = sort.and(JpaSort.of(Profile_.name).descending());
					sortBy -= 8;
				}
				if (sortBy >= 4) {
					sort = sort.and(JpaSort.of(Profile_.position).descending());
					sortBy -= 4;
				}
				if (sortBy >= 2) {
					sort = sort.and(JpaSort.of(Profile_.method).descending());
					sortBy -= 2;
				}
				if (sortBy >= 1) {
					sort = sort.and(JpaSort.of(Profile_.experience).descending());
					sortBy -= 1;
				}
			}
		}
		return sort;
	}

	/*
	 * 
	 * 1 -> by Post
	 * 2 -> by date create
	 * 
	 */
	public static Sort getReportSort(Integer sortBy, Boolean sortDescending) {
		Sort sort = Sort.unsorted();

		if (sortBy != null) {
			if (sortDescending == null || !sortDescending.booleanValue()) { // ASC
				if (sortBy >= 2) {
					sort = sort.and(JpaSort.of(Report_.date).ascending());
					sortBy -= 2;
				}
				if (sortBy >= 1) {
					sort = sort.and(JpaSort.of(Report_.post).ascending());
					sortBy -= 1;
				}
			} else { // DESC

				if (sortBy >= 2) {
					sort = sort.and(JpaSort.of(Report_.date).descending());
					sortBy -= 2;
				}
				if (sortBy >= 1) {
					sort = sort.and(JpaSort.of(Report_.post).descending());
					sortBy -= 1;
				}
			}
		}
		return sort;
	}
}
