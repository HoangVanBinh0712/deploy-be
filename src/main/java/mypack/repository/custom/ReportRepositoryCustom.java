package mypack.repository.custom;

import java.util.Date;
import java.util.List;

import mypack.model.Report;
import mypack.utility.Page;

public interface ReportRepositoryCustom {
	public List<Report> searchReport(Long postId, Boolean handle, Date date, Date handleDate, Page page);

	public Long countBeforeSearchReport(Long postId, Boolean handle, Date handleDate, Date date);

}
