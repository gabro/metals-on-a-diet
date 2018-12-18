package tests

import scala.meta.internal.metals.Buffers
import scala.meta.internal.metals.DocumentSymbolProvider
import scala.meta.internal.mtags.Semanticdbs
import scala.meta.internal.{semanticdb => s}
import MetalsTestEnrichments._

/**
 * Checks the positions of document symbols inside a document
 */
object DocumentSymbolSuite extends DirectoryExpectSuite("documentSymbol") {
  val documentSymbolProvider = new DocumentSymbolProvider(new Buffers())

  override def testCases(): List[ExpectTestCase] = {
    input.scalaFiles.map { file =>
      ExpectTestCase(
        file, { () =>
          val documentSymbols =
            documentSymbolProvider.documentSymbols(file.file)

          val textDocument = s.TextDocument(
            schema = s.Schema.SEMANTICDB4,
            language = s.Language.SCALA,
            text = file.input.text,
            occurrences = documentSymbols.map(_.toSymbolOccurrence)
          )

          Semanticdbs.printTextDocument(textDocument)
        }
      )
    }
  }

}
