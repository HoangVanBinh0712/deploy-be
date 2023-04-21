package mypack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mypack.model.Report;
import mypack.payload.statistic.StatisticForReport;
import mypack.repository.custom.ReportRepositoryCustom;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>, ReportRepositoryCustom {

	@Query(value = "Select new mypack.payload.statistic.StatisticForReport(Month(c.date) as month, handle, count(c) as count)  from Report c where YEAR(c.date) = :year and c.handle = :handle group by Month(c.date) order by Month(c.date) asc")
	List<StatisticForReport> getToTalReportByStatus(@Param("year") Integer year, @Param("handle") Boolean handle);

	@Query(value = "Select new mypack.payload.statistic.StatisticForReport(Month(c.date) as month, true, count(c) as count)  from Report c where YEAR(c.date) = :year group by Month(c.date) order by Month(c.date) asc")
	List<StatisticForReport> getToTalReport(@Param("year") Integer year);
}
